var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop14_faulty { 
	all pt : Protected & Trash | after pt not in Protected
}

run  prop14_faulty