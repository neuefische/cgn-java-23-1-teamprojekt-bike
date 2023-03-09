cd frontend || exit
npm ci
npm run build
rm -rf ../backend/src/main/resources/static
mv build ../backend/src/main/resources/static
cd ../backend || exit
./mvnw clean package
cd ..
#docker build -t mega-bike-master-9000 .