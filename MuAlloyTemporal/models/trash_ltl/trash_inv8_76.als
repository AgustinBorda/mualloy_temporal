var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 

  some f:File | some f.link
  
}

run  prop8_faulty