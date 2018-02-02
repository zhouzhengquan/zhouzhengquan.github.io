#install.packages('stringr')
library(stringr)

#process data into 1 row for same visitor ID
setwd ("C:\\Users\\zzhou\\Desktop\\eclipse\\RecommendationFP-Tree\\new\\pathdemo")
pathdata<-data.frame(read.csv('ReportData.csv',sep=',',header=TRUE,na.strings=c("NA",""),stringsAsFactors = F))
names(pathdata)<-c("id","pagename")
testdata<- subset(pathdata, select = c("id","pagename"))
nrow(testdata);

options(scipen=200)

#bind pagename

data<-data.frame(testdata[,c(1,2)])

n = length(unique(data$id))
mydata = matrix(0,nrow=n,ncol=2)
for (i in (1:n)){
  dd = data[data$id==unique(data$id)[i],]
  aa = dd[1,]$pagename
  if (nrow(dd)>1) {
    for (j in (2:nrow(dd))) {
      aa=paste(aa,dd[j,]$pagename,sep=',')
    }
  }
  else {aa=aa}
  mydata[i,]=c(aa,unique(data$id)[i])
}
mydata<-data.frame(mydata)

library(reshape)
names(mydata)<-c("pagename","visid")
mydata$visid<-(mydata$visid)
options(scipen=200)
write.csv(mydata,file="processed1.csv",row.names=F)

#setwd ("C:\\Users\\zzhou\\Desktop\\eclipse\\RecommendationFP-Tree\\new")
alldata<-data.frame(read.csv('processed1.csv',sep=',',header=TRUE,na.strings=c("NA",""),stringsAsFactors = F))
alldata<- subset(alldata, select = c("pagename"))
#split path
pathdata<-strsplit(as.character(alldata$pagename),split=",")
pathdata[[1]][1]
length(pathdata)
length(pathdata[[3]])
a<-matrix(0,length(pathdata),1)
for (i in 1:length(pathdata)){
  a[i,1]<-(length(pathdata[[i]]))
}
pathwaycol<-max(a[,1])
pathwayrow<-length(pathdata)
#put data
pathway<-data.frame(matrix(0,pathwayrow,pathwaycol))

for (i in 1: pathwayrow){
  for (j in 1:pathwaycol){
    pathway[i,j]<-pathdata[[i]][j]
  }
}
names(pathway)<-c("path1","path2","path3","path4","path5","path6")
#arrange ID based on col
pathwayID<-data.frame(matrix(0,pathwayrow,pathwaycol))
names(pathwayID)<-c("path1","path2","path3","path4","path5","path6")

##col1
col1<-pathway[,1]
col1uni<-unique(col1)
col1uni<-data.frame(col1uni[(order(col1uni))])
col1_ID<-matrix(0,nrow(col1uni),1)
for (i in 1:nrow(col1uni)){
  col1_ID[i,1]<-1000+i
}
col1IDindex<-cbind(col1uni,col1_ID)
col1IDindex<-na.omit(col1IDindex)
names(col1IDindex)<-c("colname","colID")
rownumber<-match(pathway$path1,col1IDindex$colname)
pathwayID$path1<-as.character(col1IDindex[rownumber,2])

##col2
col2<-pathway[,2]
col2uni<-unique(col2)
col2uni<-data.frame(col2uni[(order(col2uni))])
col2_ID<-matrix(0,nrow(col2uni),1)
for (i in 1:nrow(col2uni)){
  col2_ID[i,1]<-2000+i
}
col2IDindex<-cbind(col2uni,col2_ID)
col2IDindex<-na.omit(col2IDindex)
names(col2IDindex)<-c("colname","colID")
rownumber<-match(pathway$path2,col2IDindex$colname)
pathwayID$path2<-as.character(col2IDindex[rownumber,2])

##col3
col3<-pathway[,3]
col3uni<-unique(col3)
col3uni<-data.frame(col3uni[(order(col3uni))])
col3_ID<-matrix(0,nrow(col3uni),1)
for (i in 1:nrow(col3uni)){
  col3_ID[i,1]<-3000+i
}
col3IDindex<-cbind(col3uni,col3_ID)
col3IDindex<-na.omit(col3IDindex)
names(col3IDindex)<-c("colname","colID")
rownumber<-match(pathway$path3,col3IDindex$colname)
pathwayID$path3<-as.character(col3IDindex[rownumber,2])

##col4
col4<-pathway[,4]
col4uni<-unique(col4)
col4uni<-data.frame(col4uni[(order(col4uni))])
col4_ID<-matrix(0,nrow(col4uni),1)
for (i in 1:nrow(col4uni)){
  col4_ID[i,1]<-4000+i
}
col4IDindex<-cbind(col4uni,col4_ID)
col4IDindex<-na.omit(col4IDindex)
names(col4IDindex)<-c("colname","colID")
rownumber<-match(pathway$path4,col4IDindex$colname)
pathwayID$path4<-as.character(col4IDindex[rownumber,2])

##col5
col5<-pathway[,5]
col5uni<-unique(col5)
col5uni<-data.frame(col5uni[(order(col5uni))])
col5_ID<-matrix(0,nrow(col5uni),1)
for (i in 1:nrow(col5uni)){
  col5_ID[i,1]<-5000+i
}
col5IDindex<-cbind(col5uni,col5_ID)
col5IDindex<-na.omit(col5IDindex)
names(col5IDindex)<-c("colname","colID")
rownumber<-match(pathway$path5,col5IDindex$colname)
pathwayID$path5<-as.character(col5IDindex[rownumber,2])

##col6
col6<-pathway[,6]
col6uni<-unique(col6)
col6uni<-data.frame(col6uni[(order(col6uni))])
col6_ID<-matrix(0,nrow(col6uni),1)
for (i in 1:nrow(col6uni)){
  col6_ID[i,1]<-6000+i
}
col6IDindex<-cbind(col6uni,col6_ID)
col6IDindex<-na.omit(col6IDindex)
names(col6IDindex)<-c("colname","colID")
rownumber<-match(pathway$path6,col6IDindex$colname)
pathwayID$path6<-as.character(col6IDindex[rownumber,2])



a<-data.frame(matrix(0,pathwayrow,pathwaycol))
names(a)<-c("path1","path2","path3","path4","path5","path6")

for (i in 1:nrow(pathway)){
  for (j in 1:ncol(pathway)){
    if (j<2){
  a[i,j]<-str_c('{ id: ',pathwayID[i,j],', pId: ','0',", name: '",pathway[i,j],"'},")
    }else{
  a[i,j]<-str_c('{ id: ',pathwayID[i,j],', pId: ',pathwayID[i,j-1],", name: '",pathway[i,j],"'},")     
    }
  }
}


b<-data.frame(matrix(0,pathwayrow*pathwaycol,1))
for (i in 1:pathwayrow){
  for(j in 1:pathwaycol){

  b[6*i-6+j,1]<-(a[i,j])
  }
}

b<-data.frame(b[which(b != 0),])

write.table(b, file = "pathtest.csv",row.names=F,quote=F,col.names=F)
write.table(b, file = "pathtest.txt",row.names=F,quote=F,col.names=F)
