var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop4_faulty { 
  	some f: File | f not in Protected implies eventually f in Trash and no f.link

}

run  prop4_faulty