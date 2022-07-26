package monad

import monad.Monad.Monad

import scala.io.StdIn

object IoMonad extends App {

  trait IO[A] {
    self =>
    def run: A

    def map[B]: (A => B) => IO[B] = f => new IO[B] {
      override def run: B = f(self.run)
    }

    def flatMap[B]: (A => IO[B]) => IO[B] = f => new IO[B] {
      override def run: B = f(self.run).run
    }
  }


  object IO extends Monad[IO] {
    override def flatMap[A, B]: IO[A] => (A => IO[B]) => IO[B] = ioa => f => ioa flatMap f

    override def pure[A]: A => IO[A] = a => new IO[A] {
      def run: A = a
    }

    override def map[A, B]: IO[A] => (A => B) => IO[B] = ioa => f => ioa map f

    def apply[A](a: => A): IO[A] = new IO[A] {
      override def run: A = a
    }
  }

  def readLine() = IO[String] {
    StdIn.readLine()
  }

  def printLine(msg: String): IO[Unit] = IO {
    println(msg)
  }

  val run = printLine("enter your name").flatMap(_ => readLine().map(d => printLine(s"hi $d!"))).run
  run.run

  val x = for {
    _ <- printLine("enter another name")
    d <- readLine()
    - <- printLine(s"hello $d!")

  } yield ()
  x.run

}
