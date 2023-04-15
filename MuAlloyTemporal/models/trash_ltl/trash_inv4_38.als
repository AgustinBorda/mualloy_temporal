var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop4_faulty { 
  	some f: File | once f not in Protected implies eventually always f in Trash

}

run  prop4_faulty