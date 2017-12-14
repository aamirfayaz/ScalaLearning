

def filesHere = new java.io.File(".").listFiles
type FunctorType = (String,String) => Boolean
//using function literal
def filesMatching1(query:String, matcher: FunctorType) = {
  for(file <- filesHere; if matcher(file.getName, query)
   ) yield file
}
filesMatching1(".scala",(x:String,y:String) => x.endsWith(y))
filesMatching1(".scala",(x,y) => x.endsWith(y))
filesMatching1(".scala",_.endsWith(_))

filesMatching1(".scala",_.contains(_))
filesMatching1(".scala",_.matches(_))

// now using closures.
def filesMatching2(matcher: String => Boolean) = {
  for(file <- filesHere; if matcher(file.getName)
  ) yield file
}
val query = ".scala"
filesMatching2((x:String) => x.endsWith(query))
filesMatching2(x => x.endsWith(query))
filesMatching2(_.endsWith(query))

/*****currying*****/
def curriedSum(x: Int)(y: Int) = x + y
val x: Int => Int = curriedSum(1)
val y: Int => Int = curriedSum(2)_  //way to get an actual reference to curriedSum’s “second” function, not space here with _
x(2)
y(2)
