package s3.website.model

case class S3Endpoint(
  s3WebsiteHostname: String,
  s3Hostname: String
)

object S3Endpoint {
  def defaultEndpoint = S3Endpoint("us-east-1")

  val oldRegions = Seq(
    "us-east-1",
    "us-west-1",
    "us-west-2",
    "ap-southeast-1",
    "ap-southeast-2",
    "ap-northeast-1",
    "eu-west-1",
    "sa-east-1"
  )

  def apply(region: String): S3Endpoint = {
    if (region == "EU") {
      return S3Endpoint("eu-west-1")
    }

    val isOldRegion = oldRegions.contains(region)
    val s3WebsiteHostname =
      if (isOldRegion)
        s"s3-website-$region.amazonaws.com"
      else
        s"s3-website.$region.amazonaws.com"
    val s3Hostname =
      if (region == "us-east-1")
        "s3.amazonaws.com"
      else if (isOldRegion)
        s"s3-$region.amazonaws.com"
      else
        s"s3.$region.amazonaws.com"

    S3Endpoint(s3WebsiteHostname = s3WebsiteHostname, s3Hostname = s3Hostname)
  }
}