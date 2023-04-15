var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop6_faulty { 
  eventually some f:File | f not in File' and always f not in File'
}

run  prop6_faulty