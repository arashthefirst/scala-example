package typesys

object StructuralType extends App {

  def printSize[T <: {def size: Int}](t: T): Unit = println(t.size) // accepts what ever that has size method

  printSize(List(1, 2, 3))
  printSize(Seq(1, 2, 3, 4))
  printSize(Map(1 -> 2, (2, 3)))

  class MyClass {
    def size: Int = 10
  }

  printSize(new MyClass)
  printSize(new {
    def size = 9
  })

  trait Sizable {
    def size : Int
  }

  printSize(new Sizable {
    def size = 21
  })

}

