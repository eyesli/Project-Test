package groovy


import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.expr.ArgumentListExpression
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.ast.expr.MethodCall
import org.codehaus.groovy.ast.expr.PropertyExpression
import org.codehaus.groovy.ast.expr.VariableExpression
import org.codehaus.groovy.transform.stc.GroovyTypeCheckingExtensionSupport


//https://github.com/apache/groovy/blob/master/src/test-resources/groovy/transform/stc/MissingMethod2TestExtension.groovy
// 安全检查，防止调用敏感方法
class SandboxTypeCheckingExtension extends GroovyTypeCheckingExtensionSupport.TypeCheckingDSL {

    @Override
    Object run() {

        onMethodSelection { Expression expr, MethodNode methodNode ->


            if (methodNode.declaringClass.name == 'java.lang.System' ||
                    methodNode.declaringClass.name == 'java.lang.Runtime' ||
                    methodNode.declaringClass.name == 'java.lang.Class') {
                addStaticTypeError("Method is not allowed!", expr)
            }else {
                handled = true
//                return makeDynamic(expr)
            }
        }

        unresolvedVariable { VariableExpression var ->
            handled = true
            if (var.name == 'when' || var.name == 'then') {
                storeType(var, CLOSURE_TYPE)
            }
        }
        methodNotFound { ClassNode receiver, String name, ArgumentListExpression argList, ClassNode[] argTypes, MethodCall call ->

            if ('decision_rule' == name) {
                return  makeDynamic(call, CLOSURE_TYPE)
            }
            if ("compareTo"==name){
                 newMethod(name, STRING_TYPE)
            }
//            return newMethod(name, STRING_TYPE)
        }
        unresolvedProperty { PropertyExpression pexp ->
            handled = true
        }
        unresolvedAttribute { attr ->
            handled = true
        }

    }


}
