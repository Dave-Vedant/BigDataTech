object LearningScala2 {
  // Flow control
  
  // If / else syntax
  if (1 > 3) println("Impossible!") else println("The world makes sense.")
                                                  //> The world makes sense.
  
  if (1 > 3) {
  	println("Impossible!")
  } else {
  	println("The world makes sense.")
  }                                               //> The world makes sense.
  
  // TEST
  val a : Double =  3                             //> a  : Double = 3.0
  val b : Int = 5                                 //> b  : Int = 5
  if (a<b) println("x is less than y") else println("y is greater than x")
                                                  //> x is less than y
  
  // Matching - like switch in other languages:
  val number = 3                                  //> number  : Int = 3
  number match {
  	case 1 => println("One")
  	case 2 => println("Two")
  	case 3 => println("Three")
  	case _ => println("Something else")
 	}                                         //> Three
 	
 // swich TEST
 /*
 var d : Int = 0
 	for (d <- 0 to 6){
 	 	val decision = d
 		decision match{
 		case 0 => println("At 25, Start to Live")
 		case 1 => println("At 26, Live for Learning")
 		case 2 => println("At 27, Live for Desire")
 		case 3 => println("At 28, Live with love")
 		case 4 => println("At 29, Live with continuous progress")
 		case 5 => println("At 30, Live to start something")
 		}
 		}
 	
 	*/
 	// For loops ( operator <- works as in range())
 	for (x <- 1 to 4) {
 		val squared = x * x
 		println(squared)
 	}                                         //> 1
                                                  //| 4
                                                  //| 9
                                                  //| 16
                                                  
  // While loops
  var x = 10                                      //> x  : Int = 10
  while (x >= 0) {
  	println(x)
  	x -= 1
  }                                               //> 10
                                                  //| 9
                                                  //| 8
                                                  //| 7
                                                  //| 6
                                                  //| 5
                                                  //| 4
                                                  //| 3
                                                  //| 2
                                                  //| 1
                                                  //| 0
                                                  
  x = 0
  
  do { println(x); x+=1 } while (x <= 10)         //> 0
                                                  //| 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 8
                                                  //| 9
                                                  //| 10
                                                  
   // Expressions
   // "Returns" the final value in a block automatically
   
   {val x = 10; x + 20}                           //> res0: Int = 30
                                                
	 println({val x = 10; x + 20})            //> 30
	 
	 // EXERCISE
	 // Write some code that prints out the first 10 values of the Fibonacci sequence.
	 // This is the sequence where every number is the sum of the two numbers before it.
	 // So, the result should be 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
	 
	
	 for (fab <- 1 to 10){
	 	val fabonacci = (fab-1) + (fab-2)
	 	println(fabonacci + ", ")
	 	
	 	}                                 //> -1, 
                                                  //| 1, 
                                                  //| 3, 
                                                  //| 5, 
                                                  //| 7, 
                                                  //| 9, 
                                                  //| 11, 
                                                  //| 13, 
                                                  //| 15, 
                                                  //| 17, 
	 	
	   
}