package typesys

object SingletonType extends App {


  trait Inspectable {
    def inspect(): Boolean
  }

  object Inspector {
    def inspect(inspectable: Inspectable): Boolean = {
      inspectable.inspect()
    }
  }

  class Tire extends Inspectable {
    def inspect(): Boolean = true // for sake of testing
  }

  class Light extends Inspectable {
    def inspect(): Boolean = false // for sake of testing
  }

  // inspector.type is singleton type since inspector is an object
  // unlike classes you cannot use object name as type name
  def printResult(inspectable: Inspectable, inspector: Inspector.type): Unit = {
    println(s"The result if testing for ${inspectable.getClass.getSimpleName} : ${inspector.inspect(inspectable)}")
  }

  List(new Tire, new Light).foreach(printResult(_, Inspector))


}
