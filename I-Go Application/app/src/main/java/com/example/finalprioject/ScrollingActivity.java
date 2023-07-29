package com.example.finalprioject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;


public class ScrollingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private CheckBox chkArboretum;
    private CheckBox chkAlmaMater;
    private CheckBox chkFoellingerAuditorium;
    private CheckBox chkGraingerEngineeringLibraryInformationCenter;
    private CheckBox chkHalleneGateway;
    private CheckBox chkIlliniUnion;
    private CheckBox chkKrannertCenterforthePerformingArts;
    private CheckBox chkMemorialStadium;
    private CheckBox chkNationalCenterforSupercomputingApplications;
    private CheckBox chkThomasMSiebelCenterforComputerScience;
    private CheckBox chkStateFarmCenter;
    private CheckBox chkIlliniBookStore;

    private CheckBox chkAltgeldHall;
    private CheckBox chkArmory;
    private CheckBox chkAstronomicalObservatory;
    private CheckBox chkActivitiesandRecreationCenter;
    private CheckBox chkUIIceArena;
    private CheckBox chkMcFarlandBellTower;
    private CheckBox chkKrannertArtMuseumandKinkeadPavilion;
    private CheckBox chkLincolnHall;
    private CheckBox chkMorrowPlots;
    private CheckBox chkRoundDairyBarns;
    private CheckBox chkSpurlockMuseum;
    private CheckBox chkUniversityLibrary;
    private CheckBox chkBeckmanInstituteforAdvancedScienceandTechnology;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("I-GO Checklist");
        setSupportActionBar(toolbar);

        addListenerOnChkArboretum();
        addListenerOnChkAlmaMater();
        addListenerOnChkFoellingerAuditorium();
        addListenerOnChkGraingerEngineeringLibraryInformationCenter();
        addListenerOnChkHalleneGateway();
        addListenerOnChkIlliniUnion();
        addListenerOnChkKrannertCenterforthePerformingArts();
        addListenerOnChkMemorialStadium();
        addListenerOnChkNationalCenterforSupercomputingApplications();
        addListenerOnChkThomasMSiebelCenterforComputerScience();
        addListenerOnChkStateFarmCenter();
        addListenerOnChkIlliniBookStore();
        addListenerOnChkAltgeldHall();
        addListenerOnChkArmory();
        addListenerOnChkAstronomicalObservatory();
        addListenerOnChkActivitiesandRecreationCenter();
        addListenerOnChkUIIceArena();
        addListenerOnChkMcFarlandBellTower();
        addListenerOnChkKrannertArtMuseumandKinkeadPavilion();
        addListenerOnChkLincolnHall();
        addListenerOnChkMorrowPlots();
        addListenerOnChkRoundDairyBarns();
        addListenerOnChkSpurlockMuseum();
        addListenerOnChkUniversityLibrary();
        addListenerOnChkBeckmanInstituteforAdvancedScienceandTechnology();

        /**
         * Following code is to check if any ot the locations were previously checked
         */

        chkAlmaMater = findViewById(R.id.AlmaMater);
        chkAlmaMater.setChecked(getFromSP("alma mater"));
        chkAlmaMater.setOnCheckedChangeListener(this);

        chkAltgeldHall = findViewById(R.id.AltgeldHall);
        chkAltgeldHall.setChecked(getFromSP("altgeld"));
        chkAltgeldHall.setOnCheckedChangeListener(this);

        chkActivitiesandRecreationCenter = findViewById(R.id.ActivitiesandRecreationCenter);
        chkActivitiesandRecreationCenter.setChecked(getFromSP("arc"));
        chkActivitiesandRecreationCenter.setOnCheckedChangeListener(this);

        chkArboretum = findViewById(R.id.Arboretum);
        chkArboretum.setChecked(getFromSP("arboretum"));
        chkArboretum.setOnCheckedChangeListener(this);

        chkArmory = findViewById(R.id.Armory);
        chkArmory.setChecked(getFromSP("armory"));
        chkArmory.setOnCheckedChangeListener(this);

        chkAstronomicalObservatory = findViewById(R.id.AstronomicalObservatory);
        chkAstronomicalObservatory.setChecked(getFromSP("astro"));
        chkAstronomicalObservatory.setOnCheckedChangeListener(this);

        chkBeckmanInstituteforAdvancedScienceandTechnology = findViewById(R.id.BeckmanInstituteforAdvancedScienceandTechnology);
        chkBeckmanInstituteforAdvancedScienceandTechnology.setChecked(getFromSP("beckman"));
        chkBeckmanInstituteforAdvancedScienceandTechnology.setOnCheckedChangeListener(this);

        chkFoellingerAuditorium = findViewById(R.id.FoellingerAuditorium);
        chkFoellingerAuditorium.setChecked(getFromSP("foellinger"));
        chkFoellingerAuditorium.setOnCheckedChangeListener(this);

        chkGraingerEngineeringLibraryInformationCenter = findViewById(R.id.GraingerEngineeringLibraryInformationCenter);
        chkGraingerEngineeringLibraryInformationCenter.setChecked(getFromSP("grainger"));
        chkGraingerEngineeringLibraryInformationCenter.setOnCheckedChangeListener(this);

        chkHalleneGateway = findViewById(R.id.HalleneGateway);
        chkHalleneGateway.setChecked(getFromSP("hallene"));
        chkHalleneGateway.setOnCheckedChangeListener(this);

        chkIlliniUnion = findViewById(R.id.IlliniUnion);
        chkIlliniUnion.setChecked(getFromSP("union"));
        chkIlliniUnion.setOnCheckedChangeListener(this);

        chkIlliniBookStore = findViewById(R.id.Illini_Union_Bookstore);
        chkIlliniBookStore.setChecked(getFromSP("bookstore"));
        chkIlliniBookStore.setOnCheckedChangeListener(this);

        chkKrannertArtMuseumandKinkeadPavilion = findViewById(R.id.KrannertArtMuseumandKinkeadPavilion);
        chkKrannertArtMuseumandKinkeadPavilion.setChecked(getFromSP("krannert art"));
        chkKrannertArtMuseumandKinkeadPavilion.setOnCheckedChangeListener(this);

        chkKrannertCenterforthePerformingArts = findViewById(R.id.KrannertCenterforthePerformingArts);
        chkKrannertCenterforthePerformingArts.setChecked(getFromSP("krannert center"));
        chkKrannertCenterforthePerformingArts.setOnCheckedChangeListener(this);

        chkLincolnHall = findViewById(R.id.LincolnHall);
        chkLincolnHall.setChecked(getFromSP("lincoln"));
        chkLincolnHall.setOnCheckedChangeListener(this);

        chkMcFarlandBellTower = findViewById(R.id.McFarlandBellTower);
        chkMcFarlandBellTower.setChecked(getFromSP("mcfarland"));
        chkMcFarlandBellTower.setOnCheckedChangeListener(this);

        chkMemorialStadium = findViewById(R.id.MemorialStadium);
        chkMemorialStadium.setChecked(getFromSP("memorial"));
        chkMemorialStadium.setOnCheckedChangeListener(this);

        chkMorrowPlots = findViewById(R.id.MorrowPlots);
        chkMorrowPlots.setChecked(getFromSP("morrow"));
        chkMorrowPlots.setOnCheckedChangeListener(this);

        chkNationalCenterforSupercomputingApplications = findViewById(R.id.NationalCenterforSupercomputingApplications);
        chkNationalCenterforSupercomputingApplications.setChecked(getFromSP("national center"));
        chkNationalCenterforSupercomputingApplications.setOnCheckedChangeListener(this);

        chkRoundDairyBarns = findViewById(R.id.RoundDairyBarns);
        chkRoundDairyBarns.setChecked(getFromSP("round barns"));
        chkRoundDairyBarns.setOnCheckedChangeListener(this);

        chkSpurlockMuseum = findViewById(R.id.SpurlockMuseum);
        chkSpurlockMuseum.setChecked(getFromSP("spurlock"));
        chkSpurlockMuseum.setOnCheckedChangeListener(this);

        chkStateFarmCenter = findViewById(R.id.StateFarmCenter);
        chkStateFarmCenter.setChecked(getFromSP("state farm"));
        chkStateFarmCenter.setOnCheckedChangeListener(this);

        chkThomasMSiebelCenterforComputerScience = findViewById(R.id.ThomasMSiebelCenterforComputerScience);
        chkThomasMSiebelCenterforComputerScience.setChecked(getFromSP("siebel"));
        chkThomasMSiebelCenterforComputerScience.setOnCheckedChangeListener(this);

        chkUIIceArena = findViewById(R.id.UIIceArena);
        chkUIIceArena.setChecked(getFromSP("ice arena"));
        chkUIIceArena.setOnCheckedChangeListener(this);

        chkUniversityLibrary = findViewById(R.id.UniversityLibrary);
        chkUniversityLibrary.setChecked(getFromSP("library"));
        chkUniversityLibrary.setOnCheckedChangeListener(this);


    }

    /**
     * checks shared preferences to see if it location check was saved beforehand
     */

    private boolean getFromSP(String key){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("shared", android.content.Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    /**
     * to save checks in shared preferences
     */
    private void saveInSp(String key,boolean value){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("shared", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * called to check if location was checked
     * if so, save it to shared preferences
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch(buttonView.getId()) {
            case R.id.AlmaMater:
                saveInSp("alma mater", isChecked);
                break;
            case R.id.AltgeldHall:
                saveInSp("altgeld", isChecked);
                break;
            case R.id.ActivitiesandRecreationCenter:
                saveInSp("arc", isChecked);
                break;
            case R.id.Arboretum:
                saveInSp("arboretum", isChecked);
                break;
            case R.id.Armory:
                saveInSp("armory", isChecked);
                break;
            case R.id.AstronomicalObservatory:
                saveInSp("astro", isChecked);
                break;
            case R.id.BeckmanInstituteforAdvancedScienceandTechnology:
                saveInSp("beckman", isChecked);
                break;
            case R.id.FoellingerAuditorium:
                saveInSp("foellinger", isChecked);
                break;
            case R.id.GraingerEngineeringLibraryInformationCenter:
                saveInSp("grainger", isChecked);
                break;
            case R.id.HalleneGateway:
                saveInSp("hallene", isChecked);
                break;
            case R.id.IlliniUnion:
                saveInSp("union", isChecked);
                break;
            case R.id.Illini_Union_Bookstore:
                saveInSp("bookstore", isChecked);
                break;
            case R.id.KrannertArtMuseumandKinkeadPavilion:
                saveInSp("krannert art", isChecked);
                break;
            case R.id.KrannertCenterforthePerformingArts:
                saveInSp("krannert center", isChecked);
                break;
            case R.id.LincolnHall:
                saveInSp("lincoln", isChecked);
                break;
            case R.id.McFarlandBellTower:
                saveInSp("mcfarland", isChecked);
                break;
            case R.id.MemorialStadium:
                saveInSp("memorial", isChecked);
                break;
            case R.id.MorrowPlots:
                saveInSp("morrow", isChecked);
                break;
            case R.id.NationalCenterforSupercomputingApplications:
                saveInSp("national center", isChecked);
                break;
            case R.id.RoundDairyBarns:
                saveInSp("round barns", isChecked);
                break;
            case R.id.SpurlockMuseum:
                saveInSp("spurlock", isChecked);
                break;
            case R.id.StateFarmCenter:
                saveInSp("atate farm", isChecked);
                break;
            case R.id.ThomasMSiebelCenterforComputerScience:
                saveInSp("siebel", isChecked);
                break;
            case R.id.UIIceArena:
                saveInSp("ice arena", isChecked);
                break;
            case R.id.UniversityLibrary:
                saveInSp("library", isChecked);
                break;
        }
    }

    /**
     * Following functions are for the specified toast to appear once checked
     */
    public void addListenerOnChkArboretum() {
        chkArboretum = findViewById(R.id.Arboretum);
        chkArboretum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "Did you smell the flowers, like you? ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkAlmaMater() {
        chkAlmaMater = findViewById(R.id.AlmaMater);
        chkAlmaMater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, " You will come to this place again on your graduation day", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkFoellingerAuditorium() {
        chkFoellingerAuditorium = findViewById(R.id.FoellingerAuditorium);
        chkFoellingerAuditorium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "You did a good job climbing the staris.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkGraingerEngineeringLibraryInformationCenter() {
        chkGraingerEngineeringLibraryInformationCenter = findViewById(R.id.GraingerEngineeringLibraryInformationCenter);
        chkGraingerEngineeringLibraryInformationCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "One of the biggest library in the world!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkHalleneGateway() {
        chkHalleneGateway = findViewById(R.id.HalleneGateway);
        chkHalleneGateway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "You will also come back to this place when you graduate! YAY!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkIlliniUnion() {
        chkIlliniUnion = findViewById(R.id.IlliniUnion);
        chkIlliniUnion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "This place has everything: sofa, ATM, and food.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkMemorialStadium() {
        chkMemorialStadium = findViewById(R.id.MemorialStadium);
        chkMemorialStadium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "G-O! I-L-L-N-I", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkKrannertCenterforthePerformingArts() {
        chkKrannertCenterforthePerformingArts = findViewById(R.id.KrannertCenterforthePerformingArts);
        chkKrannertCenterforthePerformingArts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "Put some eye candy[ㅇ-ㅇ]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkNationalCenterforSupercomputingApplications() {
        chkNationalCenterforSupercomputingApplications = findViewById(R.id.NationalCenterforSupercomputingApplications);
        chkNationalCenterforSupercomputingApplications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "Is Jimmy Neutron here?", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkThomasMSiebelCenterforComputerScience() {
        chkThomasMSiebelCenterforComputerScience = findViewById(R.id.ThomasMSiebelCenterforComputerScience);
        chkThomasMSiebelCenterforComputerScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "Where is Geoff? Let's find Geoff!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkStateFarmCenter() {
        chkStateFarmCenter = findViewById(R.id.StateFarmCenter);
        chkStateFarmCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, " G-O! 가자! I-L-L-N-I!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkIlliniBookStore() {
        chkIlliniBookStore = findViewById(R.id.Illini_Union_Bookstore);
        chkIlliniBookStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "YES! AMAZON PICK UP Location!! ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkAltgeldHall() {
        chkAltgeldHall = findViewById(R.id.AltgeldHall);
        chkAltgeldHall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "What song is playing?", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkArmory() {
        chkArmory = findViewById(R.id.Armory);
        chkArmory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "Left, left, left, right, left!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkAstronomicalObservatory() {
        chkAstronomicalObservatory = findViewById(R.id.AstronomicalObservatory);
        chkAstronomicalObservatory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "The Universe is pretty big..", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkActivitiesandRecreationCenter() {
        chkActivitiesandRecreationCenter = findViewById(R.id.ActivitiesandRecreationCenter);
        chkActivitiesandRecreationCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "Sweat it out!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkMcFarlandBellTower() {
        chkMcFarlandBellTower = findViewById(R.id.McFarlandBellTower);
        chkMcFarlandBellTower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "Ding! Ding! Ding!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkKrannertArtMuseumandKinkeadPavilion() {
        chkKrannertArtMuseumandKinkeadPavilion = findViewById(R.id.KrannertArtMuseumandKinkeadPavilion);
        chkKrannertArtMuseumandKinkeadPavilion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "The Art Museum, not the performing center...", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkLincolnHall() {
        chkLincolnHall = findViewById(R.id.LincolnHall);
        chkLincolnHall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "Rub Lincoln's nose for good luck!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkMorrowPlots() {
        chkMorrowPlots = findViewById(R.id.MorrowPlots);
        chkMorrowPlots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "Are these plants edible?", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkRoundDairyBarns() {
        chkRoundDairyBarns = findViewById(R.id.RoundDairyBarns);
        chkRoundDairyBarns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "Was that a cow?!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkSpurlockMuseum() {
        chkSpurlockMuseum = findViewById(R.id.SpurlockMuseum);
        chkSpurlockMuseum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "So much to look at!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkUniversityLibrary() {
        chkUniversityLibrary = findViewById(R.id.UniversityLibrary);
        chkUniversityLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "Were you studying?", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkUIIceArena() {
        chkUIIceArena = findViewById(R.id.UIIceArena);
        chkUIIceArena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "Don't slip!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChkBeckmanInstituteforAdvancedScienceandTechnology() {
        chkBeckmanInstituteforAdvancedScienceandTechnology = findViewById(R.id.BeckmanInstituteforAdvancedScienceandTechnology);
        chkBeckmanInstituteforAdvancedScienceandTechnology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(ScrollingActivity.this, "Welcome to the largest building on campus!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
