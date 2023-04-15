var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop11_faulty { 
	always Protected' = Protected + (File-Trash)
}

run  prop11_faulty