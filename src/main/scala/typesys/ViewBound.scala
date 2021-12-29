package typesys

object ViewBound extends App {

  case class Person(name: String, id: Long)

  implicit def toPerson(t: (Int, String)): Person = Person(t._2, t._1)

  implicit def toPerson(t: String): Person = {
    val Array(fn, id) = t.split(",")
    Person(fn, id.toLong)
  }

  def register[T <% Person](t: T): Person = {
    println(t)
    t // <<== scala uses implicit conversion
  }

  val p1 = register("Arash,1")
  val p2 = register((1, "Arash"))

  println(p1)
  println(p2)

}
