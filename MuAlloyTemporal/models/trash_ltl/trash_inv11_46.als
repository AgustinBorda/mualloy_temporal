var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop11_faulty { 
	not Protected & Protected' = File
}

run  prop11_faulty