package ListHandling

object ListHandling extends App {

  def findmultipleofthree(value: List[Int], value2: List[Int]):Any =
  {
    var cnt1 : Int = 0
    var cnt2:  Int = 0
    for(i <- value)
      {
        if((i+1)%3 == 0)
          {
            cnt1= cnt1 +1
          }
        else
          {
            cnt2 =  cnt2+1
          }
      }

    for(i <-value2)
    {
      if((i+1)%3 == 0)
      {
        cnt1 = cnt1+1
      }
      else
      {
        cnt2 = cnt2 +1
      }
    }
    return (cnt1,cnt2)
  }
  def findpoweroftwo(value: List[Int], value1: List[Int]):Int={
    var cnt = 0
    for(i <- value)
      {
        var current = i
        while (current % 2==0)
          {
            current = current/2
          }
          if(current == 1)
            cnt = cnt + 1;
      }

    for(i <- value1)
    {
      var current = i
      while (current % 2==0)
      {
        current = current/2
      }
      if(current == 1)
        cnt = cnt +1;
    }
    return cnt

  }
  def findsum(value: List[Int], value1: List[Int]):Int ={
    var size = value.length
    var ans = 0
    for(i <- 2 to size - 2)
      {
        ans = ans + ({value(i)})
      }
      size = value1.length
    for(i <- 2 to size - 2)
      {
        ans = ans + {value1(i)}
      }
      return ans
  }
  val powersOfTwoClean = List[Int](8, 32, 2, 16384, 512)
  val powersOfTwo = List[Int](8, 32, 2, 3, 16384, 512, 9)
  // if (x + 1) of above lists is a multiple of 3
  val multipleofthreeornot=(findmultipleofthree(powersOfTwo,powersOfTwoClean))
  println(multipleofthreeornot)
  // example - 8  is 2^3 so the power value is 3. If a number is not a power of 2 print a message and discard it
  val powerValues = findpoweroftwo(powersOfTwo,powersOfTwoClean)
  println(powerValues)
  val sorted1 = powersOfTwoClean.sorted
  val sorted2 = powersOfTwo.sorted
  println(sorted1)
  println(sorted2)
  val ascendingSortedPowerValues = true
  // take 2nd element to n-2nd element and sum the values and print it
  val slicedListSum : Int = findsum(powersOfTwo,powersOfTwoClean)
  println(slicedListSum)
}

