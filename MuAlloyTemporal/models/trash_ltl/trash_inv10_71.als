var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop10_faulty { 
  all f : File {
  always (f in Protected implies f in Protected)
  always (f not in Protected implies f not in Protected)
  }
  
  
}

run  prop10_faulty