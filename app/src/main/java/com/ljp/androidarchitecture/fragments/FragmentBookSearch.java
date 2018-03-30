package com.ljp.androidarchitecture.fragments;

public class FragmentBookSearch /*extends Fragment implements Injectable */ {
   /* private List<Book> bookList = new ArrayList<>();
    private AdapterBook<Book> adapter;
    private static class MyHandler extends Handler {
        private WeakReference<FragmentBookSearch> ref;

        private MyHandler(FragmentBookSearch fragment) {
            ref = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.obj instanceof Resource) {
                Resource resource = (Resource) msg.obj;
                if (resource.data instanceof BookList) {
                    ref.get().bookList.clear();
                    ref.get().bookList.addAll( ((BookList) resource.data).getBooks());
                    ref.get().adapter .notifyDataSetChanged();
                }
            }
        }
    }

    public static final String UID_KEY = "uid";
    private ViewModelBookSearch viewModel;

    private MyHandler myHandler = new MyHandler(this);



    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        String tag = Thread.currentThread().getStackTrace()[2].getMethodName();
        Log.v(tag, tag);
        super.onActivityCreated(savedInstanceState);
        final String userId = getArguments().getString(UID_KEY);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ViewModelBookSearch.class);
        viewModel.init("yellow", 0, 20);
        viewModel.getBook().observe(FragmentBookSearch.this, book -> {
            Message msg = new Message();
            msg.obj = book;
            myHandler.sendMessage(msg);
        });

    }

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflater == null) return null;
        View root = inflater.inflate(R.layout.fragment_book_search, container, false);
        adapter = new AdapterBook<Book>(getContext(), bookList);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }*/
}
