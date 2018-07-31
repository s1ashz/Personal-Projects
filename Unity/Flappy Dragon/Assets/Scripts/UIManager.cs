using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using UnityEngine.EventSystems;

public class UIManager : MonoBehaviour {

	public static UIManager instance;
	public Text scoreText;
	public GameObject gameOverPanel;
	public Text highScoreText;
	public GameObject playerDead;

	void Awake() {
		if (instance == null) {
			instance = this;
		}
	}

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		scoreText.text = ScoreManager.instance.score.ToString();
	}

	public void GameOver() {
		gameOverPanel.SetActive (true);
		highScoreText.text = PlayerPrefs.GetInt ("HighScore").ToString ();
		//playerDead.SetActive (true);
	}

	public void Replay(){
		SceneManager.LoadScene ("Game");
	}

	public void Menu(){
		SceneManager.LoadScene ("Menu");
	}

}
