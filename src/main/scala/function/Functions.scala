package function

object Functions extends App {

  /*
   the code below will be compiled into regular java methods
   example add1:

     int add1(int a , int b ) {
         return a + b;
     }

      and

      add3:

      Function2<Integer,Integer,Integer> add3() {
        return (a,b) -> a + b ;
      }
   */
  def add1(a: Int, b: Int): Int = a + b

  def add2: (Int, Int) => Int = (a, b) => a + b

  def add3 = (a: Int, b: Int) => a + b

  def add4: (Int, Int) => Int = (a: Int, b: Int) => a + b

  /*
  the following code will be compiled to
  private static Function2<Integer,Integer,Integer> add5 = (a,b) -> a + b
  same for add6, and then there is a method that return add5 and add6

   */
  val add5: (Int, Int) => Int = (a, b) => a + b
  val add6 = (a: Int, b: Int) => a + b



  val adds = List[(Int, Int) => Int](add1, add2, add3, add4, add5, add6)
  adds.map(_ (5, 5)).foreach(println)

}
