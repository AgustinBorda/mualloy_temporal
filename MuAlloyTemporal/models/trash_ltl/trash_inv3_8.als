var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop3_faulty { 
  some Trash or some Protected
}

run  prop3_faulty