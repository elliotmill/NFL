package com.android.elliotmiller.nfl;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.elliotmiller.nfl.game.GameQuarter;
import com.android.elliotmiller.nfl.game.GameResult;

public class MainActivity extends AppCompatActivity {
    private static final String TEAM_COWBOYS = "Cowboys";
    private static final String TEAM_EAGLES = "Eagles";
    private static final String TEAM_PACKERS = "Packers";
    private static final String TEAM_BEARS = "Bears";
    private static final String TEAM_CARDINALS = "Cardinals";
    private static final String TEAM_SEAHAWKS = "SeaHawks";
    private static final String TEAM_PATRIOTS = "Patriots";
    private static final String TEAM_STEALERS = "Stealers";
    private TextView
            tvTeamOneFinalScore, tvTeamTwoFinalScore,
            tvTeam2Q4Point, tvTeam1Q4Point,
            tvTeam2Q3Point, tvTeam1Q3Point,
            tvTeam2Q2Point, tvTeam1Q2Point,
            tvTeam2Q1Point, tvTeam1Q1Point,
            noResultsView;
    private View resultsView;
    private Spinner spinnerTeams1, spinnerTeams2;

    private int flags1[] = {
            R.drawable.nfl_team,
            R.drawable.nfl_cowboys,
            R.drawable.nfl_eagles,
            R.drawable.nfl_packers,
            R.drawable.nfl_bears
    };
    private int flags2[] = {
            R.drawable.nfl_team,
            R.drawable.nfl_cardinals,
            R.drawable.nfl_seahawks,
            R.drawable.nfl_patriots,
            R.drawable.nfl_steelers
    };
    private String teams1[] = {
            "Select Team",
            TEAM_COWBOYS,
            TEAM_EAGLES,
            TEAM_PACKERS,
            TEAM_BEARS
    };
    private String[] teams2 = {
            "Select Team",
            TEAM_CARDINALS,
            TEAM_SEAHAWKS,
            TEAM_PATRIOTS,
            TEAM_STEALERS
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTeamOneFinalScore = (TextView)findViewById(R.id.tv_team1_final_score);
        tvTeamTwoFinalScore = (TextView)findViewById(R.id.tv_team2_final_score);
        tvTeam1Q4Point = (TextView)findViewById(R.id.tv_team1_q4);
        tvTeam2Q4Point = (TextView)findViewById(R.id.tv_team2_q4);
        tvTeam1Q3Point = (TextView)findViewById(R.id.tv_team1_q3);
        tvTeam2Q3Point = (TextView)findViewById(R.id.tv_team2_q3);
        tvTeam1Q2Point = (TextView)findViewById(R.id.tv_team1_q2);
        tvTeam2Q2Point = (TextView)findViewById(R.id.tv_team2_q2);
        tvTeam1Q1Point = (TextView)findViewById(R.id.tv_team1_q1);
        tvTeam2Q1Point = (TextView)findViewById(R.id.tv_team2_q1);
        spinnerTeams1 = (Spinner)findViewById(R.id.spinner_1);
        spinnerTeams2 = (Spinner)findViewById(R.id.spinner_2);
        resultsView = findViewById(R.id.results_view);
        noResultsView = (TextView)findViewById(R.id.tv_no_results);
        populateSpinners();
        setupSpinnerListeners();
    }

    private GameResult getGameScore(String team1, String team2) {
        if (team1.equalsIgnoreCase(TEAM_COWBOYS) && team2.equalsIgnoreCase(TEAM_CARDINALS)) {
            return new GameResult(
                    GameQuarter.newInstance(7, 3),
                    GameQuarter.newInstance(10, 17),
                    GameQuarter.newInstance(10, 20),
                    GameQuarter.newInstance(17, 27)
            );
        }
        if (team1.equalsIgnoreCase(TEAM_EAGLES) && team2.equalsIgnoreCase(TEAM_SEAHAWKS)) {
            return new GameResult(
                    GameQuarter.newInstance(14, 7),
                    GameQuarter.newInstance(17, 17),
                    GameQuarter.newInstance(30, 20),
                    GameQuarter.newInstance(30, 20)
            );
        }
        if (team1.equalsIgnoreCase(TEAM_PACKERS) && team2.equalsIgnoreCase(TEAM_STEALERS)) {
            return new GameResult(
                    GameQuarter.newInstance(0, 3),
                    GameQuarter.newInstance(6, 6),
                    GameQuarter.newInstance(6, 9),
                    GameQuarter.newInstance(6, 9)
            );
        }
        if (team1.equalsIgnoreCase(TEAM_BEARS) && team2.equalsIgnoreCase(TEAM_PATRIOTS)) {
            return new GameResult(
                    GameQuarter.newInstance(0, 14),
                    GameQuarter.newInstance(7, 28),
                    GameQuarter.newInstance(7, 42),
                    GameQuarter.newInstance(10, 45)
            );
        }
        return null;
    }

    private void updateResultsView(GameResult gr) {
        if (gr == null ) {
            resultsView.setVisibility(View.GONE);
            noResultsView.setVisibility(View.VISIBLE);
            int sp1 = spinnerTeams1.getSelectedItemPosition();
            int sp2 =  spinnerTeams2.getSelectedItemPosition();
            if (sp1 != 0 && sp2 != 0) {
                noResultsView.setText(getString(R.string.did_not_play));
            } else if (sp1 == 0 && sp2 != 0) {
                noResultsView.setText(getString(R.string.select_team_2));
            } else if (sp1 != 0 && sp2 == 0) {
                noResultsView.setText(getString(R.string.select_team_1));
            }
            else {
                noResultsView.setText(getString(R.string.select_teams));
            }
        } else {
            resultsView.setVisibility(View.VISIBLE);
            noResultsView.setVisibility(View.GONE);

            tvTeamOneFinalScore.setText(String.valueOf(gr.getFinalScores()[0]));
            tvTeamTwoFinalScore.setText(String.valueOf(gr.getFinalScores()[1]));

            tvTeam1Q1Point.setText(String.valueOf(gr.quarterOne.TEAM_ONE_POINT));
            tvTeam2Q1Point.setText(String.valueOf(gr.quarterOne.TEAM_TWO_POINT));

            tvTeam1Q2Point.setText(String.valueOf(gr.quarterTwo.TEAM_ONE_POINT));
            tvTeam2Q2Point.setText(String.valueOf(gr.quarterTwo.TEAM_TWO_POINT));

            tvTeam1Q3Point.setText(String.valueOf(gr.quarterThree.TEAM_ONE_POINT));
            tvTeam2Q3Point.setText(String.valueOf(gr.quarterThree.TEAM_TWO_POINT));

            tvTeam1Q4Point.setText(String.valueOf(gr.quarterFour.TEAM_ONE_POINT));
            tvTeam2Q4Point.setText(String.valueOf(gr.quarterFour.TEAM_TWO_POINT));
        }
    }

    private void setupSpinnerListeners() {
        spinnerTeams1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position != 0) {
                    int t2 = spinnerTeams2.getSelectedItemPosition();
                    if (t2 != 0) {
                        updateResultsView(getGameScore(teams1[position], teams2[t2]));
                    } else {
                        noResultsView.setText(getString(R.string.select_team_2));
                        Toast.makeText(MainActivity.this, "Select Opposing Team (Team 2)", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerTeams2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position != 0) {
                    int t1 = spinnerTeams1.getSelectedItemPosition();
                    if (t1 != 0) {
                        updateResultsView(getGameScore(teams1[t1], teams2[position]));
                    } else {
                        noResultsView.setText(getString(R.string.select_team_1));
                        Toast.makeText(MainActivity.this, "Select Opposing Team (Team 1)", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populateSpinners(){
        CustomAdapter c1 = new CustomAdapter(flags1, teams1);
        spinnerTeams1.setAdapter(c1);
        CustomAdapter c2 = new CustomAdapter(flags2, teams2);
        spinnerTeams2.setAdapter(c2);
    }

    private class CustomAdapter extends BaseAdapter {
        int flags[];
        String[] teamNames;

        public CustomAdapter(int[] flags, String[] tNames) {
            this.flags = flags;
            this.teamNames = tNames;
        }

        @Override
        public int getCount() {
            return flags.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(MainActivity.this).inflate(R.layout.team_item, null);
            ImageView icon = (ImageView) view.findViewById(R.id.imageView);
            TextView names = (TextView) view.findViewById(R.id.textView);
            icon.setImageResource(flags[i]);
            names.setText(teamNames[i]);
            return view;
        }
    }
}
