var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop10_faulty { 
	all f : Protected.link | always f in Protected
}

run  prop10_faulty