import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 
 
 
a = 1 + 2
b = a * 3
a = a / b // c=a/b
d = c == 5
 
The code above has 4 statements. Write an algorithm to calculate the final value of all variables using the code given below as a basis.
 
 
 */



class Scratch {
  public static void main(String[] args) {
    System.out.println(createTestCase());
    // write code to evaluate the return value of createTestCase()
    Block block = createTestCase();
    ExprSolver solver = new ExprSolver();
    block.accept(solver);
    for (Map.Entry<String, Float> variable : solver.variables.entrySet()) 
    {
        System.out.println(variable.getKey() + "=" + variable.getValue());
    }
    }

  static Block createTestCase() {
    return new Block(List.of(
      new Assignment("a", new Binary(new NumberValue(1), new NumberValue(2), "+")),
      new Assignment("b", new Binary(new VariableValue("a"), new NumberValue(3), "*")),
      new Assignment("c", new Binary(new VariableValue("a"), new VariableValue("b"), "/")),
    	new Assignment("d", new Binary(new VariableValue("a"), new NumberValue(5), "=="))
    ));
  }
}


class ExprSolver implements Visitor {
   private float result;

   public  Map<String, Float> variables = new HashMap<>();
   	
   private float EvaluateExpr(Expression expr)
   {
   		((Visitable)expr).accept(this);
        return result;
   }

   private float EvaluateValue(Value value)
    {
    	((Visitable)value).accept(this);
          return result;
    }
   
   @Override
   public void visitNumberValue(NumberValue value)
   {
       result = value.value();
   }
   
   @Override
   public void visitVariableValue(VariableValue variable)
   {
      // check for variable is a valid
      result = variables.get(variable.name());
   }
   
   @Override
   public void visitBinary(Binary operation)
   {
   
   float lhs = EvaluateValue(operation.lhs());
   float rhs = EvaluateValue(operation.rhs());
   
       switch(operation.op())
       {
          case "+":
          result = lhs + rhs;
          break;
          
          case "*":
          result = lhs * rhs;
          break;
          
          case "/":
          result = lhs / rhs;
          break;
          
          case "==":
          result = lhs == rhs ? 1.0f : 0.0f;
          break;
          
          default:
          //illegal operation
          System.exit(1);
       }
   }
   
   @Override
   public void visitAssignment(Assignment assignment)
   {
      float value = EvaluateExpr(assignment.rhs());
      variables.put(assignment.lhs(), value);
   }

   @Override
    public void visitBlock(Block block)
    {
        for(Assignment assignment : block.statements())
        {
            assignment.accept(this);
        }
    }   
}

interface Visitor {
  void visitBlock(Block block);

  void visitAssignment(Assignment assignment);

  void visitBinary(Binary operation);

  void visitVariableValue(VariableValue variable);

  void visitNumberValue(NumberValue value);
}

interface Visitable {
  void accept(Visitor visitor);
}

record Assignment(String lhs, Expression rhs) implements Visitable {
  @Override
  public void accept(Visitor visitor) {
    visitor.visitAssignment(this);
  }
}

interface Expression {
}

record Binary(Value lhs, Value rhs, String op) implements Expression, Visitable {
  public Value lhs() {
    return lhs;
  }

  public Value rhs() {
    return rhs;
  }

  public String op() {
    return op;
  }
  @Override
  public void accept(Visitor visitor) {
    visitor.visitBinary(this);
  }
}

interface Value extends Expression {
}

record NumberValue(float value) implements Value, Visitable {
  @Override
  public void accept(Visitor visitor) {
    visitor.visitNumberValue(this);
  }
}

record VariableValue(String name) implements Value, Visitable {
  @Override
  public void accept(Visitor visitor) {
    visitor.visitVariableValue(this);
  }
}

record Block(List<Assignment> statements) implements Visitable {
  @Override
  public void accept(Visitor visitor) {
    visitor.visitBlock(this);
  }
}