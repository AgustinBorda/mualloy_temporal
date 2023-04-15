var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop6_faulty { 
  some f : File | always f in Trash since eventually f in Trash 
}

run  prop6_faulty