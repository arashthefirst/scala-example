package implicits

object AddMethod extends App {

  implicit class StringUtils(str: String) {
    def mirror = str + "|" + str.reverse
  }

  println("Babak".mirror)
}
