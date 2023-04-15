var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop18_faulty { 
	always all f: File | f in Protected and f in Trash implies always f not in Protected
}

run  prop18_faulty