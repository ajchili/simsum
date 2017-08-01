# simsum
A [Tesseract](https://github.com/tesseract-ocr/tesseract) and [tess4j](https://github.com/nguyenq/tess4j) based Java OCR program.

## setup and usage
1. Download and install Tesseract
   * This can be done multiple ways, please view the [Tesseract wiki](https://github.com/tesseract-ocr/tesseract/wiki#installation) for detailed instructions for your platform
2. Download [simsum](https://github.com/ajchili/simsum/releases)
3. Launch simsum
   1. Start by selecting a file/folder that contains images of text that you would like simsum to parse
   2. Navigate to your file inside of simsum if more than one file have been selected or added
   3. Either press `Read text` without changing the image location and scale **OR** move the image and scale it accordingly to "read" the shown part of the image, then press `Read text`

## use in an IDE or help develop simum
1. simsum requires Tesseract and tess4j, meaning both must be installed
   * The installation of Tesseract is explained in the setup
   * tess4j is being used through maven, you will have to add it to the project manually
2. After installing Tesseract and tess4j, you can run the program in your IDE, it should work _(if it does not, create an issue [here](https://github.com/ajchili/simsum/issues))_
3. Make changes to either suit your needs and/or improve simsum
