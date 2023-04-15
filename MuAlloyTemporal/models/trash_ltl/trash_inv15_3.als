var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop15_faulty { 
	
  	always (no File & Trash and eventually some File & Trash)
}

run  prop15_faulty