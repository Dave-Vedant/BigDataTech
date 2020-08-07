object LearningScala2 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(141); 
  // Flow control
  
  // If / else syntax
  if (1 > 3) println("Impossible!") else println("The world makes sense.");$skip(96); 
  
  if (1 > 3) {
  	println("Impossible!")
  } else {
  	println("The world makes sense.")
  };$skip(35); 
  
  // TEST
  val a : Double =  3;System.out.println("""a  : Double = """ + $show(a ));$skip(18); 
  val b : Int = 5;System.out.println("""b  : Int = """ + $show(b ));$skip(75); 
  if (a<b) println("x is less than y") else println("y is greater than x");$skip(68); 
  
  // Matching - like switch in other languages:
  val number = 3;System.out.println("""number  : Int = """ + $show(number ));$skip(146); 
  number match {
  	case 1 => println("One")
  	case 2 => println("Two")
  	case 3 => println("Three")
  	case _ => println("Something else")
 	};$skip(540); 
 	
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
 	};$skip(81); 
                                                  
  // While loops
  var x = 10;System.out.println("""x  : Int = """ + $show(x ));$skip(47); 
  while (x >= 0) {
  	println(x)
  	x -= 1
  };$skip(59); 
                                                  
  x = 0;$skip(45); 
  
  do { println(x); x+=1 } while (x <= 10);$skip(154); val res$0 = 
                                                  
   // Expressions
   // "Returns" the final value in a block automatically
   
   {val x = 10; x + 20};System.out.println("""res0: Int = """ + $show(res$0));$skip(82); 
                                                
	 println({val x = 10; x + 20});$skip(353); 
	 
	 // EXERCISE
	 // Write some code that prints out the first 10 values of the Fibonacci sequence.
	 // This is the sequence where every number is the sum of the two numbers before it.
	 // So, the result should be 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
	 
	
	 for (fab <- 1 to 10){
	 	val fabonacci = (fab-1) + (fab-2)
	 	println(fabonacci + ", ")
	 	
	 	}}
	 	
	   
}
