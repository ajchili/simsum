//
//  ParsedImage.swift
//  simsum
//
//  Created by Kirin Patel on 5/9/18.
//  Copyright © 2018 Kirin Patel. All rights reserved.
//

import Foundation
import UIKit
import FirebaseMLVision

class ParsedImage {
    
    var text: String?
    var image: UIImage?
    var features: [VisionText]?
    
    func getTextFromImage(textDetector: VisionTextDetector, textView: UITextView) {
        let vImage = VisionImage(image: self.image!)
        let metadata = VisionImageMetadata()
        metadata.orientation = .topLeft
        textDetector.detect(in: vImage) { (features, error) in
            guard error == nil, let features = features, !features.isEmpty else {
                return
            }
            
            self.features = features
            self.text = ""
            
            for feature in features {
                self.text = "\(self.text!)\n\n\(feature.text)"
            }
            
            textView.text = self.text
        }
    }
}
