package slickakkahttp.rest

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import org.json4s.{DefaultFormats, Extraction}
import org.json4s.jackson.JsonMethods._
import slickakkahttp.Controllers.EmployeeControllerComponent
import slickakkahttp.Entities.Employee
import slickakkahttp.Utilities.ImplEmployeeRepository

import scala.concurrent.ExecutionContext.Implicits.global

class EmployeeRest(actorRef: ActorRef, controller: EmployeeControllerComponent) extends Directives {
  implicit val system = ActorSystem.create("Test")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  implicit val f = DefaultFormats
  val routes = get {
    pathSingleSlash {
      complete{
        HttpResponse(status = StatusCodes.OK, entity = HttpEntity(MediaTypes.`application/json`, compact(Extraction.decompose(""))))
      }
    }
  }
}
  /*path("employee") {
    post {
       headerValueByName("apiKey") { token =>
        authorize(validateApiKey(token)) {
      entity(as[String]) { data =>
        complete {
          controller.insertEmployeeController(actorRef, data).map { result =>
            result map { finalResult =>
              HttpResponse(status = StatusCodes.OK, entity = HttpEntity(MediaTypes.`application/json`, compact(Extraction.decompose(finalResult))))
            }
          }
        }
      }
        }
       }
    } ~ get {
      complete {
        ImplEmployeeRepository.getAll.map { result =>
          HttpResponse(status = StatusCodes.OK, entity = HttpEntity(MediaTypes.`application/json`, compact(Extraction.decompose(result))))
        }
      }

    }
  } ~ path("employee" / "employeeId" / LongNumber) { id =>
    delete {
      complete {
        ImplEmployeeRepository.deleteRecord(id).map { result =>
          HttpResponse(status = StatusCodes.OK, entity = HttpEntity(MediaTypes.`application/json`, compact(Extraction.decompose(result))))
        }
      }
    } ~ put {
      entity(as[String]) { data =>
        complete {
          val dd = parse(data).extract[Employee]
          ImplEmployeeRepository.updateEmployee(id, dd).map { result =>
            HttpResponse(status = StatusCodes.OK, entity = HttpEntity(MediaTypes.`application/json`, compact(Extraction.decompose(result))))
          }
        }/*recover {
            case ex => val (statusCode, message) = handleErrorMessages(ex)
              if (statusCode == StatusCodes.NoContent)
                HttpResponse(status = statusCode)
              else
                HttpResponse(status = statusCode, entity = HttpEntity(MediaTypes.`application/json`, message.asJson))
          }*/
      }
    }
  } ~ pathPrefix("employeeByName") {
    path(Rest) { name =>
      get {
        complete {
          ImplEmployeeRepository.getEmployeeByName(name).map { result =>
            HttpResponse(status = StatusCodes.OK, entity = HttpEntity(MediaTypes.`application/json`, compact(Extraction.decompose(result))))
          }

        }
      }
    }
  }

    def validateApiKey(apiKey: String): Boolean = {
    val apiKeysJson = ConfigFactory.load().getString("apiKeys").trim
    val apiKeys = Nil//extractEntityWithTry[ApiKeys](apiKeysJson).getOrElse(ApiKeys(List()))
    //if (apiKeys.values.contains(apiKey)) true else false
    //f (apiKeys.contains(apiKey)) true else false
      true
  }

/*
   def handleErrorMessages(ex: Throwable) = {
    ex.printStackTrace()

    ex match {
      case cmd: DuplicateNameException => {
        //logger.error("Exception occurred. " + stackTraceAsString(cmd.exception))
         (StatusCodes.Conflict, ErrorMessageContainer(cmd.message))
      }
  
  }}
  case class DuplicateNameException(errorCode:String = ErrorCodes.DUPLICATE_NAME, message:String = ErrorMessages.DUPLICATE_NAME, exception:Throwable)
object ErrorCodes {
  val DUPLICATE_NAME:String = "1000"
  }
  object ErrorMessages {
   val DUPLICATE_NAME:String = "Duplicate Name"
   }
   case class ErrorMessageContainer(message: String, ex: Option[String] = None, code: String = "")
*/*/


//}