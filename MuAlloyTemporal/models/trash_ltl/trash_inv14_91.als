var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop14_faulty { 
  	all p: (Protected & Trash) | after p not in Protected
}

run  prop14_faulty