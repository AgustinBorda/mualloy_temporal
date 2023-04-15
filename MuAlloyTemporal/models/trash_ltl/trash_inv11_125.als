var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop11_faulty { 
 	 (File - Protected) not in Protected after (File - Protected) in Protected
}

run  prop11_faulty