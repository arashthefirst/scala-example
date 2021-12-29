package implicits

object Conversion extends App {

  implicit def fromInt(x: Int): String = String.valueOf(x)
  implicit def fromDouble(x: Double): String = String.valueOf(x)

  val i : String = 1 // uses fromInt to convert
  val d : String = 1.2 // uses fromDouble to  convert



}
