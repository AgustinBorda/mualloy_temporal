var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop5_faulty { 
	eventually some f : File| f in Trash and File' = File - f and Trash' = Trash - f
}

run  prop5_faulty