using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FrontBackgroundController : MonoBehaviour {

	public float speed;
	private Rigidbody2D rb;

	// Use this for initialization
	void Start () {
		rb = GetComponent<Rigidbody2D> ();
		moveBackGround ();
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void moveBackGround() {
		rb.velocity = new Vector2 (-speed, 0);
	}

	void OnTriggerEnter2D(Collider2D col) {
		

	}

}
