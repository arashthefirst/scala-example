package implicits

object ImplicitFunctionAndObj extends App {

  type Predicate = Person => Boolean
  case class Person(name: String, id: Long)
  implicit val predicate: Predicate = p => p.name == "John" && p.id == 1
  implicit val p: Person = Person("John", 1)


  def printPerson(implicit p: Person): Unit = {
    println(p)
  }

  def testPerson(implicit person: Person, predicate: Predicate): Boolean = {
    predicate(person)
  }

  printPerson
  println(testPerson)

}
