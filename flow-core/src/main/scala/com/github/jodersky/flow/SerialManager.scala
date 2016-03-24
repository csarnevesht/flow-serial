package com.github.jodersky.flow

import java.nio.ByteBuffer

import akka.util.ByteString
import com.github.jodersky.flow.internal.SerialConnection

/**
 * Entry point to the serial API.
 */
class SerialManager  {

  // TODO: set in application.conf?
  val port = "/dev/cu.usbmodem1411"
  val settings = SerialSettings(115200, 8, false, Parity(0))

  val bufferSize = 1024
  val readBuffer = ByteBuffer.allocateDirect(bufferSize)
  val writeBuffer = ByteBuffer.allocateDirect(bufferSize)
  private val builder = new StringBuilder()

  def open(port: String = port, settings: SerialSettings = settings): SerialConnection = synchronized {
    SerialConnection.open(port, settings)
  }

  def close(connection: SerialConnection) = {
    connection.close()
  }

  def sendRequest(connection: SerialConnection, input: String) = {
    writeBuffer.clear()
    writeBuffer.put((input+System.lineSeparator()).getBytes())
    val sent = connection.write(writeBuffer)
  }

  def getResponse(serial: SerialConnection) : String = {
    val endOfDataToken = ">"
    var stop = false
    while (!serial.isClosed && !stop) {
      try {
        readBuffer.clear()
        val length = serial.read(readBuffer)
        readBuffer.limit(length)

        val data = ByteString.fromByteBuffer(readBuffer)
        val str = new String(data.toArray, "UTF-8")
        builder.append(new String(data.toArray, "UTF-8"))
        if(str.contains(endOfDataToken) ) {
          stop = true
        }
      } catch {

        //don't do anything if port is interrupted
        case ex: PortInterruptedException => {}

        case ex: Exception => {
          println("exception " + ex.toString)
          stop = true
        }
      }
    }
    builder.toString
  }

}

object SerialManager {

}
