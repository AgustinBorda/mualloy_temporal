var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop12_faulty { 
	some f: File |always( eventually always f in Trash)
}

run  prop12_faulty