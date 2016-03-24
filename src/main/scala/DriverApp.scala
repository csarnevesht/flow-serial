

object DriverApp extends App {

  val serialManager = new SerialManager

  val connection = serialManager.open()

  serialManager.sendRequest(connection, "about")
  val modemResponse = serialManager.getResponse(connection)

  serialManager.sendRequest(connection, "help")
  val downloadResponse = serialManager.getResponse(connection)

  connection.close

}
