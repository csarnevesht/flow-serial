package com.github.jodersky.flow

import java.nio.ByteBuffer

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
    //    val data = ByteString((input+System.lineSeparator()).getBytes())
    writeBuffer.clear()
    writeBuffer.put((input+System.lineSeparator()).getBytes())
    //    data.copyToBuffer(writeBuffer)
    val sent = connection.write(writeBuffer)
  }

  def getResponse(serial: SerialConnection) = {
    val endOfDataToken = ">"
    var stop = false
    while (!serial.isClosed && !stop) {
      try {
        readBuffer.clear()
        val length = serial.read(readBuffer)
        readBuffer.limit(length)
        val data = readBuffer.toString
        builder.append(readBuffer.toString)
        //        val data = ByteString.fromByteBuffer(readBuffer)
        if(data.contains(endOfDataToken) ) {
          stop = true
        }
      } catch {

        //don't do anything if port is interrupted
        case ex: PortInterruptedException => {}

        case ex: Exception => {
          stop = true
        }
      }
    }
  }

}

object SerialManager {

}
