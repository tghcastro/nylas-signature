import com.nylas.Notification

import javax.crypto.spec.SecretKeySpec
import javax.crypto.Mac
import java.nio.charset.StandardCharsets
import java.util.Base64
import java.util.Arrays
import java.util.List;


// brew install sbt
// brew install scala


object Test {

  def main(args: Array[String]) = {

    var jsonBody = "{\"name\":\"aaaaa\"}"
    var clientSecret = "nylas_secret"

    var signature = getSignature(jsonBody, clientSecret)
    println("Signature: " + signature)
    println("IsValid: " + Notification.isSignatureValid(jsonBody, clientSecret, signature))
  }

  def getSignature(body: String, clientSecret: String) : String = {

    val secretKeySpec = new SecretKeySpec(clientSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

    val mac = Mac.getInstance("HmacSHA256")
    mac.init(secretKeySpec)

    var sigBytes = mac.doFinal(body.getBytes(StandardCharsets.UTF_8));
    var exaBodyBytes = convertBytesToHex(sigBytes)
    var signatureBody = new String(Base64.getEncoder.encode(exaBodyBytes.getBytes));
    signatureBody
  }

  // Is it working correctly? :thinking_face:
  def convertBytesToHex(bytes: Seq[Byte]) : String = {
    val sb = new StringBuilder
    for (b <- bytes) {
      sb.append(String.format("%02x", Byte.box(b)))
    }
    sb.toString
  }
}
