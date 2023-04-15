var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop4_faulty { 
  	some f: File | (always f not in Protected) implies eventually (always f in Trash) and eventually (always f.link in Trash)

}

run  prop4_faulty