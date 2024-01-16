import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class HotelReservation extends JFrame {
	Scanner input = new Scanner(System.in);

	private static final String CSV_FILE_PATH = "src/reservation.csv";
	private static final int THREAD_POOL_SIZE = 4;
	public boolean LoadCheck = false;

	private JButton reservationsButton;
	private JButton servicesButton;
	private JButton cityButton;
	private JButton SearchButton;
	private JButton SaveButton;
	private JButton LoadButton;
	private JMenuBar menuBar;
	private JTextArea textArea;

	LinkedList<Calculable> calculableList = new LinkedList<Calculable>();
	ArrayList<Reservation> reservationList = new ArrayList<Reservation>();

	public HotelReservation() {

		JMenu fileMenu = new JMenu("File");

		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);

		JMenu newMenu = new JMenu("New");
		JMenuItem createReservation = new JMenuItem("Reservation");
		newMenu.add(createReservation);
		JMenuItem createService = new JMenuItem("Service");
		newMenu.add(createService);

		JMenu helpMenu = new JMenu("Help");
		JMenuItem contentsItem = new JMenuItem("Contents");
		helpMenu.add(contentsItem);
		JMenuItem aboutItem = new JMenuItem("About");
		helpMenu.add(aboutItem);

		reservationsButton = new JButton("Display Reservations");
		servicesButton = new JButton("Display Extra Services");
		cityButton = new JButton("Disp. Res. For City");
		SearchButton = new JButton("Multithread Search");
		SaveButton = new JButton("Save Reservations");
		LoadButton = new JButton("Load Reservations");

		menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(newMenu);
		menuBar.add(helpMenu);
		textArea = new JTextArea(5, 5);

		textArea.setEditable(false);

		setPreferredSize(new Dimension(535, 396));
		setLayout(null);

		add(reservationsButton);
		add(servicesButton);
		add(cityButton);
		add(SearchButton);
		add(SaveButton);
		add(LoadButton);
		add(menuBar);
		add(textArea);

		reservationsButton.setBounds(75, 35, 185, 20);
		servicesButton.setBounds(275, 35, 185, 20);
		cityButton.setBounds(75, 60, 185, 20);
		SearchButton.setBounds(275, 60, 185, 20);
		SaveButton.setBounds(50, 430, 185, 20);
		LoadButton.setBounds(300, 430, 185, 20);
		menuBar.setBounds(0, 0, 635, 25);
		textArea.setBounds(15, 90, 505, 335);

		createReservation.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Reservation reservation = new Reservation();
				reservationList.add(reservation);
				Reservation.totalNumOfReservations++;
				reservation.setCustomerID(Reservation.GetCountReservations());

			}
		});

		reservationsButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String reservations = "";
				for (Reservation rez : reservationList) {

					reservations += "Reservation ID " + rez.CustomerID + "#" + "\n";
					reservations += rez;
					reservations += "\n";
				}

				textArea.setText(reservations);
				reservations = "";

			}
		});

		createService.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int opt = Integer
						.parseInt(JOptionPane.showInputDialog("Please select one of the extra services from below:\n"
								+ "1. Laundry Service\n" + "2. Spa Service"));
				if (opt == 1) {
					Laundry laundry = new Laundry();
					calculableList.add(laundry);
					int id = Integer
							.parseInt(JOptionPane.showInputDialog("Type the reservation ID to credit this service: "));
					if (id <= Reservation.GetCountReservations())
						laundry.setCustomerID(id);
					else {
						while (!(id <= Reservation.GetCountReservations())) {
							JOptionPane.showMessageDialog(null, "İnvalid ID", "ERROR", JOptionPane.ERROR_MESSAGE);
							id = Integer.parseInt(
									JOptionPane.showInputDialog("Type the reservation ID to credit this service: "));
							laundry.setCustomerID(id);
						}
					}
					try {
						laundry.setClothingPieces(
								Integer.parseInt(JOptionPane.showInputDialog("How many pieces of clothing? ")));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Clothing count must be a numeric value!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						laundry.setClothingPieces(
								Integer.parseInt(JOptionPane.showInputDialog("How many pieces of clothing? ")));
					}
				} else if (opt == 2) {
					Spa spa = new Spa();
					calculableList.add(spa);
					int id = Integer
							.parseInt(JOptionPane.showInputDialog("Type the reservation ID to credit this service:"));
					if (id <= Reservation.GetCountReservations())
						spa.setCustomerID(id);
					else {
						while (!(id <= Reservation.GetCountReservations())) {
							JOptionPane.showMessageDialog(null, "İnvalid ID", "ERROR", JOptionPane.ERROR_MESSAGE);
							id = Integer.parseInt(
									JOptionPane.showInputDialog("Type the reservation ID to credit this service:"));
							spa.setCustomerID(id);
						}
					}
					try {
						spa.setDays(Integer.parseInt(JOptionPane.showInputDialog("How many days? ")));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Day count must be a numeric value!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						spa.setDays(Integer.parseInt(JOptionPane.showInputDialog("How many days? ")));
					}
					System.out.println();

				}
			}
		});

		servicesButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String services = "";

				for (Calculable serv : calculableList) {
					if (serv instanceof Laundry) {
						services += "Reservation ID " + ((Services) serv).getCustomerID() + " has "
								+ ((Laundry) serv).getClothingPieces() + " pieces assigned for Laundry Service\n";
					} else if (serv instanceof Spa) {
						services += "Reservation ID " + ((Services) serv).getCustomerID() + " has "
								+ ((Spa) serv).getDays() + " days of SPA services\n";
					}
				}
				textArea.setText(services);
				services = "";
			}
		});

		cityButton.addActionListener(new ActionListener() {
			String city = "";

			public void actionPerformed(ActionEvent e) {
				String cityname = JOptionPane.showInputDialog("Type a city name: ");
				for (Reservation item : reservationList) {

					String cityName = item.getCityName();
					if (cityName.equals(cityname)) {
						city += "Reservation for " + cityname + ":\n";
						city += "Reservation ID " + item.getCustomerID() + "#\n";
						city += item.toString();
					}

				}
				textArea.setText(city);
				city = "";
			}
		});

		SearchButton.addActionListener(new ActionListener() {
			String rezForSearch = "";

			public void actionPerformed(ActionEvent e) {
				if (Reservation.totalNumOfReservations < 8) {
					JOptionPane.showMessageDialog(null, "Please enter at least 8 reservations - now it is only "
							+ Reservation.totalNumOfReservations, "Message", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String searchName = JOptionPane.showInputDialog("Enter Hotel Name: ");
					rezForSearch = "Reservation IDs for " + searchName + ":\n";

					ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

					ConcurrentLinkedQueue<Integer> searchResults = new ConcurrentLinkedQueue<>();

					for (Reservation rez : reservationList) {

						executor.submit(() -> {
							if (rez.getHotelName().equals(searchName)) {
								searchResults.add(rez.getCustomerID());
							}
						});

					}

					executor.shutdown();
					try {
						executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}

					for (Integer result : searchResults) {
						rezForSearch += result + " ";
					}

					textArea.setText(rezForSearch);
				}
			}
		});

		exitItem.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.exit(0);
			}
		});

		contentsItem.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				JOptionPane.showMessageDialog(null,
						"You can add reservation or service by using the 'New' button \n "
								+ "if you want you can access the information by pressing the relevant buttons.",
						"Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		aboutItem.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				JOptionPane.showMessageDialog(null,
						" Furkan Ersel\n 20200702055\n" + " furkan.ersel@std.yeditepe.edu.tr \n", "About Developer",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		SaveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (LoadCheck) {
					try {
						FileWriter writer = new FileWriter(CSV_FILE_PATH);
						BufferedWriter bufferedWriter = new BufferedWriter(writer);

						bufferedWriter.write("");
						bufferedWriter.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}

				try {
					FileWriter writer = new FileWriter(CSV_FILE_PATH, true);
					BufferedWriter bufferedWriter = new BufferedWriter(writer);
					for (Reservation rez : reservationList) {
						bufferedWriter
								.write(rez.getCityName() + "," + rez.getHotelName() + "," + rez.getReservationMonth()
										+ "," + rez.getReservationStart() + "," + rez.getReservationEnd());
						bufferedWriter.newLine();
					}
					bufferedWriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, "Saved!", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		LoadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!LoadCheck) { 
					try {
						LoadCheck = true;
						FileReader reader = new FileReader(CSV_FILE_PATH);
						BufferedReader bufferedReader = new BufferedReader(reader);

						textArea.setText("");

						String line;
						while ((line = bufferedReader.readLine()) != null) {
							String[] fields = line.split(",");
							if (fields.length == 5) {
								String Cityname = fields[0];
								String Hotelname = fields[1];
								String Monthname = fields[2];
								int StartDay = Integer.parseInt(fields[3]);
								int EndDay = Integer.parseInt(fields[4]);

								Reservation rez = new Reservation(Cityname, Hotelname, Monthname, StartDay, EndDay);
								reservationList.add(rez);
								Reservation.totalNumOfReservations++;
								rez.setCustomerID(Reservation.GetCountReservations());

							}
						}
						String reservations = "";
						for (Reservation rez1 : reservationList) {

							reservations += "Reservation ID " + rez1.CustomerID + "#" + "\n";
							reservations += rez1;
							reservations += "\n";
						}

						textArea.setText(reservations);
						reservations = "";

						bufferedReader.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			}
		});

	}

	public static void main(String[] args) {

		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.setTitle("Hotel Reservation System");
		hotelReservation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hotelReservation.setSize(550, 500);
		hotelReservation.isResizable();
		hotelReservation.setLocationRelativeTo(null);
		hotelReservation.setVisible(true);
	}
};