package scalasestspecifistyles

/**
  * Refer:-https://www.youtube.com/watch?v=W9yMkao_AZg
  */
import org.joda.time.LocalDate
import org.scalatest.FunSuite

/*class ListTest1 extends WordSpec with Matchers {

  "List Test" should {
    "test if two unsorted list are equal" in {
      List(1, 2, 3, 4) should contain theSameElementsAs List(2, 3, 1, 4)
    }
  }
}*/

object LocalDateCheckTest1 extends App {
  def isALocalDateBetweenTwoLocalDate(date:LocalDate,startDate:LocalDate,endDate:LocalDate) = {
    !date.isBefore(startDate) && !date.isAfter(endDate)
  }
  def areLocalDatesBetweenTwoLocalDates(date1: LocalDate,date2: LocalDate, startDate: LocalDate, endDate: LocalDate) = {
    !date1.isBefore(startDate) && !date2.isAfter(endDate)
  }
}

class LocalDateCheckTest extends FunSuite {

  test("isALocalDateBetweenTwoLocalDate returns true as LocalDate passed falls in the range") {
    val date = LocalDate.parse("2016-12-08")
    val startDate = LocalDate.parse("2016-12-02")
    val endDate = LocalDate.parse("2016-12-30")
    val result = LocalDateCheckTest1.isALocalDateBetweenTwoLocalDate(date, startDate, endDate)
    assert(result)
  }
  test("calling head on empty list throws NoSuchElementException") {
    intercept[NoSuchElementException] {
      List.empty.head
    }
  }
  test("isALocalDateBetweenTwoLocalDate returns false as LocalDate passed doesn't falls in the range") {
    val date = LocalDate.parse("2016-12-01")
    val startDate = LocalDate.parse("2016-12-10")
    val endDate = LocalDate.parse("2016-12-30")
    val result = LocalDateCheckTest1.isALocalDateBetweenTwoLocalDate(date,startDate,endDate)
    assert(!result)

  }
  test("areLocalDatesBetweenTwoLocalDates returns true as LocalDates range passed falls in the range") {
    val date1 = LocalDate.parse("2016-12-08")
    val date2 = LocalDate.parse("2016-12-12")
    val startDate = LocalDate.parse("2016-12-02")
    val endDate = LocalDate.parse("2016-12-30")
    val result = LocalDateCheckTest1.areLocalDatesBetweenTwoLocalDates(date1,date2,startDate,endDate)
    assert(result)

  }
  test("areLocalDatesBetweenTwoLocalDates returns false as LocalDates range passed doesn't falls in the range") {
    val date1 = LocalDate.parse("2016-12-08")
    val date2 = LocalDate.parse("2016-12-12")
    val startDate = LocalDate.parse("2016-12-02")
    val endDate = LocalDate.parse("2016-12-04")
    val result = LocalDateCheckTest1.areLocalDatesBetweenTwoLocalDates(date1,date2,startDate,endDate)
    assert(!result)

  }
}