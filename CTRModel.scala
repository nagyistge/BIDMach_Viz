import BIDMat.{Dict, IDict, FMat, SMat, SBMat}
import BIDMat.MatFunctions._
import BIDMat.SciFunctions._

/**
  * A class containing the CTR model computed offline. By matrix factorization,
  * CTR_matrix = posComponent * adKwComponent.
  *
  * @param adMap a BIDMat SBMat, each column is an ad ID.
  * @param kwMap a BIDMat SBMat, each column is a keyphrase.
  * @param posComponent a len(rank) * 1 matrix.
  * @param adKwComponent a 1 * (number of ad-keyphrase pair) matrix.
  */

class CTRModel(adMap: SBMat, kwMap: SBMat, posComponent: SMat, adKwComponent: SMat) {

  /** Convert the mapping matrix into BIDMat Dict so that we can use ad/keyphrase to get their index.*/
  val adDict = Dict(adMap)
  val kwDict = Dict(kwMap)
  val adKwDict = IDict(adKwMap)

  def getCTR(rank: Int, ad: String, kw: String): Float = {
    val ad_kw = row(adDict(ad), kwDict(kw))
    posComponent(rank, 1) * adKwComponent(1, adKwDict(ad_kw))
  }

}