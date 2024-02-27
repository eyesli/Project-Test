package groovy


import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.expr.ArgumentListExpression
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.ast.expr.MethodCall
import org.codehaus.groovy.ast.expr.PropertyExpression
import org.codehaus.groovy.ast.expr.VariableExpression
import org.codehaus.groovy.transform.stc.GroovyTypeCheckingExtensionSupport

//    GroovyTypeCheckingExtensionSupport groovyTypeCheckingExtensionSupport = new GroovyTypeCheckingExtensionSupport();


// 安全检查，防止调用敏感方法
class SandboxTypeCheckingExtension extends GroovyTypeCheckingExtensionSupport.TypeCheckingDSL {

    @Override
    Object run() {

        onMethodSelection { Expression expr, MethodNode methodNode ->

            if (methodNode.name=='decision_rule') {
                return makeDynamic(expr, CLOSURE_TYPE)
            }
            if (methodNode.declaringClass.name == 'java.lang.System' ||
                    methodNode.declaringClass.name == 'java.lang.Runtime' ||
                    methodNode.declaringClass.name == 'java.lang.Class') {
                addStaticTypeError("Method is not allowed!", expr)
            }
        }

        unresolvedVariable { VariableExpression var ->
            if (var.name == 'when' || var.name == 'then') {
                return makeDynamic(var, CLOSURE_TYPE)
            }
        }
        methodNotFound { ClassNode receiver, String name, ArgumentListExpression argList, ClassNode[] argTypes, MethodCall call ->

            if (isMethodCallExpression(call)
                    && call.implicitThis
                    && 'decision_rule' == name
                    && argTypes.length == 2
                    && argTypes[0] == classNodeFor(String)
            ) {
                makeDynamic(call, CLOSURE_TYPE)
            }
        }
        unresolvedProperty { PropertyExpression pexp ->
            makeDynamic(pexp)
        }

    }


}
