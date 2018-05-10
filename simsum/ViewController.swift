//
//  ViewController.swift
//  simsum
//
//  Created by Kirin Patel on 5/8/18.
//  Copyright © 2018 Kirin Patel. All rights reserved.
//

import UIKit
import FirebaseMLVision

class ViewController: UIViewController, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    
    lazy var vision = Vision.vision()
    var textDetector: VisionTextDetector?
    var image: UIImage?
    
    let getImageButton: UIButton = {
        let button = UIButton(type: .system)
        button.setTitle("Set Image...", for: .normal)
        button.addTarget(self, action: #selector(getImageButtonPressed), for: .touchUpInside)
        return button
    }()
    
    let imageView: UIImageView = {
        let iv = UIImageView()
        return iv
    }()
    
    let textView: UITextView = {
        let tv = UITextView()
        return tv
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        textDetector = vision.textDetector()
        
        view.addSubview(getImageButton)
        getImageButton.anchor(top: view.safeAreaLayoutGuide.topAnchor, left: view.safeAreaLayoutGuide.leftAnchor, bottom: nil, right: view.safeAreaLayoutGuide.rightAnchor, paddingTop: 0, paddingLeft: 0, paddingBottom: 0, paddingRight: 0, width: 0, height: 0)
        
        view.addSubview(imageView)
        imageView.anchor(top: getImageButton.bottomAnchor, left: view.safeAreaLayoutGuide.leftAnchor, bottom: nil, right: view.safeAreaLayoutGuide.rightAnchor, paddingTop: 8, paddingLeft: 8, paddingBottom: 0, paddingRight: 8, width: 0, height: 300)
        
        view.addSubview(textView)
        textView.anchor(top: imageView.bottomAnchor, left: view.safeAreaLayoutGuide.leftAnchor, bottom: view.safeAreaLayoutGuide.bottomAnchor, right: view.safeAreaLayoutGuide.rightAnchor, paddingTop: 8, paddingLeft: 8, paddingBottom: 8, paddingRight: 8, width: 0, height: 0)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        if image == nil {
            return
        }
        
        let a = ParsedImage()
        a.image = image
        a.getTextFromImage(textDetector: textDetector!, textView: textView)
        
        imageView.image = a.image!
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        dismiss(animated: true, completion: nil)
    }
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : Any]) {
        guard let selectedImage = info[UIImagePickerControllerOriginalImage] as? UIImage else {
            fatalError("Expected a dictionary containing an image, but was provided the following \(info)")
        }
        
        image = selectedImage
        
        dismiss(animated: true, completion: nil)
    }
    
    @objc func getImageButtonPressed() {
        let imagePickerController = UIImagePickerController()
        
        imagePickerController.sourceType = .photoLibrary
        
        imagePickerController.delegate = self
        present(imagePickerController, animated: true, completion: nil)
    }
}

