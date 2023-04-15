var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop14_faulty { 
	always(all f:File&Protected&Trash | after f in File&Trash)
}

run  prop14_faulty