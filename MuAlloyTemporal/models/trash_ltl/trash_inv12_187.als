var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop12_faulty { 
  
	
  	always all f:File | f in File implies eventually f in Trash
  
}

run  prop12_faulty