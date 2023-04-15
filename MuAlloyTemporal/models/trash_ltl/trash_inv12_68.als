var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop12_faulty { 
  always (all f:File | after f in Trash implies  some (f &Trash) )
}

run  prop12_faulty