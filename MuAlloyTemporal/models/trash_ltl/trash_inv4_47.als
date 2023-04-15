var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop4_faulty { 
  	some f: File | always( eventually f in Trash implies always f in Trash)

}

run  prop4_faulty